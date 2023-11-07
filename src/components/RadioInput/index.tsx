import React, { InputHTMLAttributes } from 'react';
import { InputWrapper, Label, Label2, Option, Input } from './styles';

interface RadioGroupProps extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
  options: string[];
}

export const RadioGroup: React.FC<RadioGroupProps> = ({ label, options, ...rest }) => {
  return (
    <InputWrapper>
      <Label>{label}</Label>
      <Option>
      {options.map((option) => (
        <Label2 key={option}>
          <Input
            type="radio"
            value={option}
            {...rest}
            />
          {option}
        </Label2>
      ))}
      </Option>
    </InputWrapper>
  );
};