package ip.rijksmuseumquiz.api.controllers;

import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ip.rijksmuseumquiz.api.models.*;
import ip.rijksmuseumquiz.domain.IQuestion;
import ip.rijksmuseumquiz.domain.IQuestionMaker;
import ip.rijksmuseumquiz.domain.QuestionMaker;
import ip.rijksmuseumquiz.persistence.IUserRepository;
import ip.rijksmuseumquiz.persistence.UserRepositoryDatabase;

@Controller
@RequestMapping("rijksmuseumquiz")
public class RijksmuseumquizController {
	private int resultsPerPage = 100;

	private IQuestionMaker questionMaker = new QuestionMaker();
	private IUserRepository userRepository = new UserRepositoryDatabase();

	@Autowired
	private RijksmuseumquizRestController rijksmuseumquizRestController;

	@GetMapping("/getquestion")
	@ResponseBody
	public ResponseEntity<QuestionDTO> getQuestion() {
		String initialQuery = rijksmuseumquizRestController.makeInitialQuery(resultsPerPage);

		int numberOfPages = questionMaker.getNumberOfPages(initialQuery, resultsPerPage);

		String specificPageResults = rijksmuseumquizRestController.getMultipleArtworks(resultsPerPage, numberOfPages);

		int numberOfObjects = questionMaker.getNumberOfObjects(specificPageResults);

		int[] randomNumbers = ThreadLocalRandom.current().ints(0, numberOfObjects).distinct().limit(4).toArray();

		String randomObjectCode = questionMaker.getRandomObjectCode(specificPageResults, randomNumbers[0]);
		String[] incorrectAnswers = questionMaker.getMultipleTitles(specificPageResults, randomNumbers[1],
				randomNumbers[2], randomNumbers[3]);

		String specificObjectResults = rijksmuseumquizRestController.getSpecificArtwork(randomObjectCode);
		String correctAnswerTitle = questionMaker.getLongTitle(specificObjectResults);
		String correctAnswerDescription;
		try {
			correctAnswerDescription = questionMaker.getPlaqueDescription(specificObjectResults);
		} catch (JSONException e) {
			correctAnswerDescription = "";
		}

		IQuestion question = questionMaker.createQuestion(correctAnswerTitle, incorrectAnswers, correctAnswerDescription);

		QuestionDTO questionDTO = new QuestionDTO(question, randomObjectCode);

		return ResponseEntity.ok().body(questionDTO);
	}

	@PostMapping(value = "/getquestionsubimage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getQuestionSubImage(@RequestBody ObjectSearchInputDTO body) {
		String specificObjectResults = rijksmuseumquizRestController.getSpecificArtwork(body.getObjectCode());
		BufferedImage image = null;
		try {
			image = questionMaker.getSubImage(specificObjectResults);
		} catch (URISyntaxException e) {
			System.out.println("There was a problem getting the URI");
		} catch (IOException e) {
			System.out.println("There was a problem with the input or output");
		}
		byte[] imageBytes = getImageBytes(image);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imageBytes, responseHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/getquestionfullimage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getQuestionFullImage(@RequestBody ObjectSearchInputDTO body) {
		String specificObjectResults = rijksmuseumquizRestController.getSpecificArtwork(body.getObjectCode());
		BufferedImage image = null;
		try {
			image = questionMaker.getImage(specificObjectResults);
		} catch (URISyntaxException e) {
			System.out.println("There was a problem getting the URI");
		} catch (IOException e) {
			System.out.println("There was a problem with the input or output");
		}
		byte[] imageBytes = getImageBytes(image);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imageBytes, responseHeaders, HttpStatus.OK);
	}

	@PostMapping("/loginuser")
	@ResponseBody
	public ResponseEntity<UserDTO> logInUser(@RequestBody UserscoreInputDTO body) {
		String username = body.getUsername();
		double userscore = userRepository.logInUser(username);
		UserDTO userDTO = new UserDTO(username, userscore);
		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping("/getuserscore")
	@ResponseBody
	public ResponseEntity<UserDTO> getUserScore(@RequestBody UserscoreInputDTO body) {
		String username = body.getUsername();
		double userscore = userRepository.getUserScore(username);
		UserDTO userDTO = new UserDTO(username, userscore);
		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping("/updateuserscore")
	@ResponseBody
	public HttpStatus updateUserScore(@RequestBody UserscoreInputDTO body) {
		String username = body.getUsername();
		int scoreIncrease = body.getScoreIncrease();
		userRepository.updateUserScore(username, scoreIncrease);
		return HttpStatus.OK;
	}

	@GetMapping("/getleaderboard")
	@ResponseBody
	public ResponseEntity<UserDTO[]> getLeaderboard() {
		HashMap<String, Double> leaderboard = userRepository.getLeaderboard();
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		for (Map.Entry<String, Double> entry : leaderboard.entrySet()) {
			String username = entry.getKey();
			Double userscore = entry.getValue();
			UserDTO user = new UserDTO(username, userscore);
			userList.add(user);
		}
		Collections.sort(userList, Comparator.comparing(UserDTO::getUserscore).reversed());
		UserDTO[] users = userList.toArray(new UserDTO[userList.size()]);
		return ResponseEntity.ok().body(users);	
	}

	private byte[] getImageBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
			ImageIO.write(image, "jpeg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
        byte[] imageBytes = baos.toByteArray();
		return imageBytes;
	}
}
