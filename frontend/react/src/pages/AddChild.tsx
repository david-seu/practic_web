import { Button } from '@mui/material';
import { useState } from 'react';
import { Form } from 'react-bootstrap';
import { createChild } from '../services/ChildService';
import Child from '../model/Child';

export default function AddChild() {
  const [name, setName] = useState<string>('');
  const [pId, setPId] = useState<number>(0);

  const handleSubmit = () => {
    const child: Child = {
      id: 0,
      name,
      checked: false,
      pId
    };

    createChild(child).then((response) => {
      console.log(response);
    });
  };

  return (
    <div>
      <h1>Add Child</h1>
      <Form noValidate className="gap-2">
        <Form.Group className="mb-3" controlId="formName">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter name"
            required
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>
        <Button onClick={handleSubmit} type="submit">
          Submit
        </Button>
      </Form>
    </div>
  );
}
