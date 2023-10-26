// External Libraries
import React from "react";

// Components

// Styles
import {
  SuccessModalButton,
  SuccessModalContainer,
  SuccessModalContent,
  SuccessModalText,
  SuccessModalTitle,
} from "./styles";

interface Props{
  onClose: () => void
}

export const SucessModal: React.FC<Props> = ({onClose}) => {
  return (
    <SuccessModalContainer>
      <SuccessModalContent>
        <SuccessModalTitle>Cadastro bem-sucedido!</SuccessModalTitle>
        <SuccessModalText>Sua conta foi criada com sucesso.</SuccessModalText>
        <SuccessModalButton onClick={onClose}>Fazer Login</SuccessModalButton>
      </SuccessModalContent>
    </SuccessModalContainer>
  );
};
