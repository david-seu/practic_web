import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Home from './pages/Home';
import AddParent from './pages/AddParent';
import UpdateParent from './pages/UpdateParent';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/parent/new" element={<AddParent />} />
        <Route path="/parent/:id" element={<UpdateParent />} />
        <Route path="/child/new" element={<AddChild />} />
        <Route path="/child/:id" element={<UpdateChild />} />
      </Routes>
    </Router>
  );
}

export default App;
