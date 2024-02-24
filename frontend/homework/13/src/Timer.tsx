import { useEffect, useRef, useState } from "react";


export function Timer() {
    const timerRef = useRef<number | null>(null);
    const [seconds, setSeconds] = useState(0);
  
    useEffect(() => {
      timerRef.current = setInterval(() => {
        setSeconds(prevSeconds => prevSeconds + 1);
      }, 1000);
  
      return () => {
        if (timerRef.current) {
          clearInterval(timerRef.current);
        }
      };
    }, []);
  
    const stopTimer = () => {
      if (timerRef.current !== null) {
        clearInterval(timerRef.current);
      }
    };
  
    const resumeTimer = () => {
      console.log(timerRef.current)
  
        console.log("here")
        timerRef.current = setInterval(() => {
          setSeconds(prevSeconds => prevSeconds + 1);
        }, 1000);
      
    };
  
    return (
      <div>
        <h2>Timer: {seconds} seconds</h2>
        <button onClick={stopTimer}>Stop Timer</button>
        <button onClick={resumeTimer}>Resume Timer</button>
      </div>
    );
  };
  