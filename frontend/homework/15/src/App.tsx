
import { useDispatch, useSelector } from 'react-redux';
import './App.css'
import { RootState } from './redux/Store';
import { decrement, decrementBy, increment, incrementBy, setInputState1, setInputState2 } from './redux/CounterSlice';

function App() {

  const reduxDispatch = useDispatch();
  const count = useSelector((state:RootState) => state.counter.count);
 const inputNumber = useSelector((state:RootState) => state.counter.inputState1);
 const inputNumber2 = useSelector((state:RootState)=>state.counter.inputState2);
const decrementCountHandler = () => {
  reduxDispatch(decrement());
};
const incrementCountHandler = () => {
  reduxDispatch(increment());
};
const incrementByHandler = () => {
  reduxDispatch(incrementBy(inputNumber));
}
const decrementByHandler = () => {
  reduxDispatch(decrementBy(inputNumber));
}
const setInputNumber=(inputNumber:number)=>{
  reduxDispatch(setInputState1(inputNumber))
}
const setInputNumber2=(inputNumber2:number)=>{
  reduxDispatch(setInputState2(inputNumber2))
}
const buttonStyle:React.CSSProperties={
  margin:"0 10px",
  border:"none",
  borderRadius:"5px",
}
  return (
   <>
   <button onClick={decrementCountHandler} style={buttonStyle}>-</button>
   <span>{count}</span>
   <button onClick={incrementCountHandler} style={buttonStyle}>+</button>
   <input type="number" value={inputNumber} onChange={(e)=>{
    setInputNumber(e.target.valueAsNumber)
   }}/>
      <button onClick={incrementByHandler} style={buttonStyle}>Increment by {inputNumber}</button>
      <input type="number" value={inputNumber2} onChange={(e)=>{
    setInputNumber2(e.target.valueAsNumber)
   }}/>
      <button onClick={decrementByHandler} style={buttonStyle}>Decrement by {inputNumber2}</button>

   </>
  )
}

export default App
