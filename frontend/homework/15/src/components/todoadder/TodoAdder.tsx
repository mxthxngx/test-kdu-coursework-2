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
  /**
   * Set the item list using the provided array of TodoInterface.
   *
   * @param {TodoInterface[]} itemList - The array of TodoInterface to set as the item list.
   * @return {void} 
   */
  const setItemListHandler = (itemList:TodoInterface[]) => {
    reduxDispatch(setItemList(itemList))
  }
  const item = useSelector((state:RootState)=>state.adder.item)
  /**
   * Sets the item using the provided value.
   *
   * @param {string} item - the item to be set
   * @return {void} 
   */
  const setItemHandler = (item:string) => {
    reduxDispatch(setItem(item))
  }

  /**
   * Add an item to the item list if the item is not empty.
   *
   * @return {void} 
   */
  const addItem = () => {
    if (item !== "") {
      setItemHandler(""); 
      setItemListHandler([...itemList, { id: Date.now(), todo: item, isDone: false,isStriked:false }]);
    }
  }

  /**
   * A function that handles the input change event.
   *
   * @param {React.ChangeEvent<HTMLInputElement>} event - the input change event
   * @return {void} 
   */
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
