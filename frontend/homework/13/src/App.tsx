import  { useRef, useEffect, useState, CSSProperties } from 'react';

const ScrollToTop = () => {
  const scrollToTopRef = useRef<HTMLDivElement>(null);

  const scrollToTop = () => {
    if (scrollToTopRef.current) {
      scrollToTopRef.current.scrollTo({top:0,behavior:'smooth'});
    }
  };
  const styleList:{[key:string]:CSSProperties}  =
  {
    paragraph : {
      width:'100px',
    }
  }  
  return (
    <div ref={scrollToTopRef} style={{ height: '500px', overflow: 'auto' }}>
      <p style={styleList.paragraph}>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Modi dolorum, odit numquam eum, rem excepturi dolorem praesentium vel, odio facere aspernatur perspiciatis minima saepe vero voluptatem officiis? Minima, totam id!
      Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo quam veritatis repudiandae vitae. Sunt sit a sint adipisci temporibus quidem ipsum voluptatem, hic, laboriosam, tenetur qui soluta odio repellat voluptas.
      </p>
      <button onClick={scrollToTop}>Scroll To Top</button>
      
    
    </div>
  );
};

const FocusFirstInput = () => {
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

const Timer = () => {
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

const App = () => {
  return (
    <div>
      <h1>Today's Homework - Explore the useRef hook</h1>
      <h2>ScrollToTop behavior using useRef:</h2>
      <ScrollToTop />
      <h2>Focus on the first input field using useRef:</h2>
      <FocusFirstInput />
      <h2>Timer Application using useRef:</h2>
      <Timer />
    </div>
  );
};

export default App;
