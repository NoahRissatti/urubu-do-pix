import "./App.css";
import { TitledInput } from "./components/TitledInput";

function App() {
  return (
    <TitledInput
      label="Nome:"
      type="text"
      placeholder="Digite seu nome"
      name="nome"
    />
  );
}

export default App;
