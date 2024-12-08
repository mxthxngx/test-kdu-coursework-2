import styled from '@emotion/styled';

interface TabProps {
  active: boolean;
}

export const Underline = styled.div<TabProps>`
  cursor: pointer;
  color: black; 
  text-decoration: none; 
  position: relative;

  &:after {
    content: '';
    display: ${(props) => (props.active ? 'block' : 'none')}; 
    width: 100%;
    height: 2px;
    background-color: #1871c2; 
    position: absolute;
    bottom: 0;
    left: 0;
  }
`;
