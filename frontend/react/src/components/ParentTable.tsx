import { Table } from '@mui/material';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Parent from '../model/Parent';
import { fetchParents } from '../services/ParentService';

export default function ParentTable() {
  const [parents, setParents] = useState<Parent[]>([]);
  const navigate = useNavigate();

  useEffect(
    () => {
      fetchParents().then((data) => setParents(data));
    },
    [],
  );

  const handleDoubleClick = (parent: Parent) => {
    navigate(`/parent/${parent.id}`);
  };

  return (
    <Table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Checked</th>
        </tr>
      </thead>
      <tbody>
        {parents.map((parent: Parent) => (
          <tr
            key={parent.id}
            onDoubleClick={() => handleDoubleClick(parent)}
          >
            <td>{parent.name}</td>
            <td className="button-container">
              <input
                type="checkbox"
                onChange={(e) => {
                  // eslint-disable-next-line no-param-reassign
                  parent.checked = e.target.checked;
                }}
              />
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}
