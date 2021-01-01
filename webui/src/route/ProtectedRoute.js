import {Redirect, Route} from "react-router-dom";

const ProtectedRoute = ({
                            component: Component,
                            isAuthenticated,
                            location,
                            redirectRoute,
                            ...rest
                        }) => {

    return (
        <Route
            render={(props) =>
                isAuthenticated === true ? (
                    <Component {...props} />
                ) : (
                    <Redirect
                        to={{
                            pathname: redirectRoute,
                            state: {from: location},
                        }}
                    />
                )
            }
            {...rest}
        />
    );
};

export default ProtectedRoute;
