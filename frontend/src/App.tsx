import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { apiUrl } from './config';


function App() {
  const [count, setCount] = useState(0)
  const [data, setData] = useState(null);

  /**
   * Reference: https://react.dev/learn/synchronizing-with-effects#fetching-data
   * https://react.dev/learn/synchronizing-with-effects#fetching-data
   * If your Effect fetches something, the cleanup function should either abort the fetch or ignore its result
   */
  useEffect(() => {
    let ignore = false;
    const fetchData = async () => {
      const response = await fetch(`${apiUrl}/user`);
      const jsonData = await response.json();
      if (!ignore) {
        console.log(jsonData);
        setData(jsonData);
      }
    };

    fetchData();

    return () => {
      ignore = true;
    }
  }, []);

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>TestVite   + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
