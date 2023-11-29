import { createContext, useContext, useState } from "react";
import { User } from "../types";

type ContextType = {
    user: User | undefined,
    setUser: (user: User) => void;
}

const UserContext = createContext<ContextType>({
    user: undefined,
    setUser() { },
});

type Props = React.PropsWithChildren;

export const UserProvider = (props: Props) => {
    const { children } = props;

    const [user, setUser] = useState<User | undefined>(undefined);

    return <UserContext.Provider value={{
        user: user,
        setUser: setUser
    }}>{children}</UserContext.Provider>
}

export const useUser = () => {
    const context = useContext(UserContext);

    if (context === undefined) {
        throw new Error('useUser must be used within a UserProvider');
    }

    return context;
}
