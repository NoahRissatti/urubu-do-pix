// External Libraries
import React, { InputHTMLAttributes } from 'react'
import { Input, InputWrapper, Label } from './styles';

// Components

// Styles
interface GenericInputProps extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
}

export const TitledInput: React.FC<GenericInputProps> = ({ label, ...rest }) => {
  return (
    <InputWrapper>
      <Label>{label}</Label>
      <Input {...rest} />
    </InputWrapper>
  );
};
