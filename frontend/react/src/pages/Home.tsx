import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import ChildrenTable from '../components/ChildTable';
import ParentTable from '../components/ParentTable';

export default function Home() {
  const navigator = useNavigate();

  const handleAddChildButton = () => {
    navigator('/child/new');
  };

  const handleAddParentButton = () => {
    navigator('/parent/new');
  };

  return (
    <div>
      <nav>
        <Button onClick={handleAddChildButton}>Add Child</Button>
        <Button onClick={handleAddParentButton}>Add Parent</Button>
      </nav>
      <h1>Home</h1>
      <ChildrenTable />
      <br />
      <ParentTable />
    </div>
  );
}
