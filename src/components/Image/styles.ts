import styled from "styled-components";

interface ImageWrapperProps {
  width?: number;
  height?: number;
}

export const ImageWrapper = styled.img<ImageWrapperProps>`
  max-width: 100%;
  height: auto;
  border-radius: 5px;
  
  width: ${(props) => (props.width ? `${props.width}px` : "auto")};
  height: ${(props) => (props.height ? `${props.height}px` : "auto")};
`;
