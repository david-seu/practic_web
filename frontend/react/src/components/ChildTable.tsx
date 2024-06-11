import { Table } from '@mui/material';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Child from '../model/Child';
import { fetchChildren } from '../services/ChildService';

export default function ChildrenTable() {
  const [children, setChildrens] = useState<Child[]>([]);
  const navigate = useNavigate();

  useEffect(
    () => {
      fetchChildren().then((data) => setChildrens(data));
    },
    [],
  );

  const handleDoubleClick = (child: Child) => {
    navigate(`/child/${child.id}`);
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
        {children.map((child: Child) => (
          <tr
            key={child.id}
            onDoubleClick={() => handleDoubleClick(child)}
          >
            <td>{child.name}</td>
            <td className="button-container">
              <input
                type="checkbox"
                onChange={(e) => {
                  // eslint-disable-next-line no-param-reassign
                  child.checked = e.target.checked;
                }}
              />
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}
