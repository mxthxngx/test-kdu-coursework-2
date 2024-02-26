import './style.css';
import { Items } from '../items/Items';
import { TodoInterface } from '../../TodoInterface';
import { Header } from '../header/Header';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { setItem, setItemList } from '../redux/ItemSlice';

export function TodoAdder() {
  const reduxDispatch = useDispatch();
  
  const itemList = useSelector((state:RootState)=>state.adder.itemList)
  const setItemListHandler = (itemList:TodoInterface[]) => {
    reduxDispatch(setItemList(itemList))
  }
  const item = useSelector((state:RootState)=>state.adder.item)
  const setItemHandler = (item:string) => {
    reduxDispatch(setItem(item))
  }
 

  const addItem = () => {
    if (item !== "") {
      setItemHandler(""); 
      setItemListHandler([...itemList, { id: Date.now(), todo: item, isDone: false }]);
    }
  }

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
   console.log(event.target.value)
    setItemHandler(event.target.value); 
  }

  return (
          <div className='main-container'>
            <Header />
            <div className="body">
              <div className="todo-header">
                <h2>Add Items</h2>
                <div className="todo-input">
                  <input type="text" value={item} onChange={handleInputChange} />
                  <button className="submit-btn" onClick={addItem}>Submit</button>
                </div>
                <Items />
              </div>
            </div>
          </div>
   
  );
}
