import {useEffect} from "react";
import {Redirect} from "react-router-dom";
import {useScrum} from "../context/ScrumProvider";

export default function SignOutPage({redirectTo}) {
    const {signOut} = useScrum();

    useEffect(() => {
        signOut();
    }, []);

    return (
        <Redirect
            to={{
                pathname: {redirectTo},
            }}
        />
    );
}
