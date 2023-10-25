// External Libraries
import React from "react";

// Components

// Styles
import { Container, Content } from "./styles";
import { TitledInput } from "../../components/TitledInput";
import { Button } from "../../components/Button";

export const Login: React.FC = () => {
  return (
    <Container>
      <Content>
        <TitledInput label={""}/>
        <TitledInput label={""}/>
        <Button label={""}/>
      </Content>
    </Container>
  );
};
