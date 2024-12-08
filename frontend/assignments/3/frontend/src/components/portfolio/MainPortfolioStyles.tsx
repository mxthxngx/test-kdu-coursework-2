import styled from "@emotion/styled";
import { Button, Container } from "@mui/material";
interface Props {
  isMobileView: boolean;
}
export const StyledContainer = styled(Container)<Props>`
  width: ${(props) => (props.isMobileView ? "80vw" : "20%")};
  background-color: #f0f0f0;
  margin: 2rem;
  height: fit-content;
  border: 1px solid grey;
  border-radius: 1rem;
  padding: 1rem;
  position: ${(props) => (props.isMobileView ? "relative" : "sticky")};
  top: 0;
  left: 0;
`;
export const StyledHr = styled.hr`
  color: #d0d0d0;
  background-color: #d0d0d0;
  height: 1px;
  border: none;
  margin-bottom: 1rem;
`;

export const ButtonStyle = styled(Button)`
color: "#1871c2",
backgroundColor: "transparent",
boxShadow: "none",
`;

export const DivMainContainer = styled.div`
display: "flex",
alignItems: "center",
width: "80%",
border: "1px solid #d0d0d0",
borderRadius: "1rem",
padding: "0.5rem",
backgroundColor: "#f8f8f8",
`;

export const SearchInputStyle = styled.input`
width: "100%",
border: "none",
outline: "none",
backgroundColor: "transparent",
fontFamily: "Poppins, sans-serif",
`;
export const DateInputStyle = styled.input`
width: "48%",
border: "1px solid #d0d0d0",
borderRadius: "1rem",
padding: "0.5rem",
backgroundColor: "#f8f8f8",
`;