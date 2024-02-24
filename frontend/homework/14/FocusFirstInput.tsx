import { useEffect, useRef } from "react";

export function FocusFirstInput()  {
    const firstInputRef = useRef<HTMLInputElement>(null);
  
    useEffect(() => {
      if (firstInputRef.current) {
        firstInputRef.current.focus();
      }
    }, []);
  
    return (
      <form>
        <label htmlFor="firstInput">First Name:</label>
        <input id="firstInput" ref={firstInputRef} />
        <br />
        <label htmlFor="lastInput">Last Name:</label>
        <input id="lastInput" />
      </form>
    );
  };