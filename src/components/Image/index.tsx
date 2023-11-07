import React, { ImgHTMLAttributes } from "react";
import { ImageWrapper } from "./styles";

interface GenericImageProps extends ImgHTMLAttributes<HTMLImageElement> {
  src: string;
  alt: string;
  width?: number; // Prop para a largura da imagem
  height?: number; // Prop para a altura da imagem
}

export const Image: React.FC<GenericImageProps> = ({ src, alt, width, height, ...rest }) => {
  return <ImageWrapper src={src} alt={alt} width={width} height={height} {...rest} />;
};
