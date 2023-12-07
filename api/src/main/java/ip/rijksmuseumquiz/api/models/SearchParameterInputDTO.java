package ip.rijksmuseumquiz.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SearchParameterInputDTO {
    String searchParameters;

    @JsonCreator
    public SearchParameterInputDTO(String searchParameters){
        this.searchParameters = searchParameters;
    }

    public String getSearchParameters(){
        return searchParameters;
    }
}
