// External Libraries
import React, { ButtonHTMLAttributes } from "react";
import { ButtonGeneric } from "./styles";

// Components

// Styles

interface GenericButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  label: string;
}

export const Button: React.FC<GenericButtonProps> = ({ label, ...rest }) => {
  return <ButtonGeneric {...rest}>{label}</ButtonGeneric>;
};
