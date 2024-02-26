import { Items } from '../items/Items';
import { TodoInterface } from '../../TodoInterface';
import { Header } from '../header/Header';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { setItem, setItemList } from '../redux/ItemSlice';

export function TodoAdder() {
  const cssStyles: { [key: string]: React.CSSProperties } = {
   
    todoInput: {
      display: 'flex',
    },
    submitBtn: {
      backgroundColor: 'rgb(26, 25, 25)',
      color: 'white',
      borderRadius: '2px',
      border: 'none'
    },
    input: {
      border: 'gainsboro 1px solid',
      height: '30px',
    }
  };

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

  return   (
    <div className='main-container'>
      <Header />
      <div className="body">
        <div style={cssStyles.todoHeader}>
          <h2>Add Items</h2>
          <div style={cssStyles.todoInput}>
            <input type="text" value={item} onChange={handleInputChange} style={cssStyles.input} />
            <button className="submit-btn" style={cssStyles.submitBtn} onClick={addItem}>Submit</button>
          </div>
          <Items />
        </div>
      </div>
    </div>
  );
}
