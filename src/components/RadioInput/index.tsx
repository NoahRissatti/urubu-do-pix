import React, { InputHTMLAttributes } from 'react';
import { InputWrapper, Label } from './styles';

interface RadioGroupProps extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
  options: string[];
}

export const RadioGroup: React.FC<RadioGroupProps> = ({ label, options, ...rest }) => {
  return (
    <InputWrapper>
      <Label>{label}</Label>
      {options.map((option) => (
        <label key={option}>
          <input
            type="radio"
            value={option}
            {...rest}
          />
          {option}
        </label>
      ))}
    </InputWrapper>
  );
};