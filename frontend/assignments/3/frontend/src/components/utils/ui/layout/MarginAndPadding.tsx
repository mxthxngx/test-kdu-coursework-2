import styled from '@emotion/styled'

interface BoxProps {
  margin?: string;
  padding?: string;
  mobilemargin?:string;
  mobilepadding?:string
}

export const StyledBox = styled.div<BoxProps>`
  margin: ${(props) => props.margin || '0'};
  padding: ${(props) => props.padding || '0'};

  @media (max-width: 768px) {
    margin: ${(props) => props.mobilemargin || props.margin};
    padding: ${(props) => props.mobilepadding || props.padding};
  }
`;
