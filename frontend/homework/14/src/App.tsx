import { Timer } from './Timer';
import { FocusFirstInput } from './FocusFirstInput';
import { ScrollToTop } from './ScrollToTop';




export function App() {
  return (
    <div>
      <h1>Exploring Useref Hooks</h1>
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
