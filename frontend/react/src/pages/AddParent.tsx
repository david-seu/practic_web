import { Button } from '@mui/material';
import { useState } from 'react';
import { Form } from 'react-bootstrap';
import Parent from '../model/Parent';
import { createParent } from '../services/ParentService';

export default function AddParent() {
  const [name, setName] = useState<string>('');

  const handleSubmit = () => {
    const parent: Parent = {
      id: 0,
      name,
      checked: false,
    };

    createParent(parent).then((response) => {
      console.log(response);
    });
  };

  return (
    <div>
      <h1>Add Parent</h1>
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
