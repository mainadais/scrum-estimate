import {ScrumProvider} from "./context/ScrumProvider";
import App from "./App";

const AppContainer = () => {
    return (
        <ScrumProvider>
            <App/>
        </ScrumProvider>
    );
};

export default AppContainer;
