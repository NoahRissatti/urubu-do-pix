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
import { Link } from "react-router-dom";

interface Props {
  onClose: () => void;
}

export const SuccessModal: React.FC<Props> = ({ onClose }) => {
  return (
    <SuccessModalContainer>
      <SuccessModalContent>
        <SuccessModalTitle>Cadastro bem-sucedido!</SuccessModalTitle>
        <SuccessModalText>Sua conta foi criada com sucesso.</SuccessModalText>
        <Link to={'/'}>
          <SuccessModalButton onClick={onClose}>Fazer Login</SuccessModalButton>
        </Link>
      </SuccessModalContent>
    </SuccessModalContainer>
  );
};
