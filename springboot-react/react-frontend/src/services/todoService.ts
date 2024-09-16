// Filepath: /Users/softdev/Desktop/github-projects/react-ecommerce-store/src/services/todoService.ts

export interface Todo {
  id: number;
  title: string;
}

// LOCAL BACKEND PORT
const API_URL = 'http://127.0.0.1:8080';

export const fetchTodos = async (): Promise<Todo[]> => {
  try {
    const response = await fetch(`${API_URL}/todo`);
    if (!response.ok) {
      console.error("Failed to fetch todos:", response.statusText);
      return [];
    }
    const data = await response.json();

    // Ensure that data.todos is an array
    if (!Array.isArray(data.todos)) {
      console.error("Todos response is not an array");
      return [];
    }

    return data.todos;
  } catch (error) {
    console.error("An error occurred while fetching todos:", error);
    return [];
  }
};

export const addTodo = async (title: string): Promise<void> => {
  try {
    const response = await fetch(`${API_URL}/todo`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title }),
    });

    if (!response.ok) {
      throw new Error('Failed to add todo');
    }
  } catch (error) {
    console.error("An error occurred while adding a todo:", error);
    throw error;
  }
};

export const updateTodo = async (id: number, title: string): Promise<void> => {
  try {
    const response = await fetch(`${API_URL}/todo/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ title }),
    });

    if (!response.ok) {
      throw new Error('Failed to update todo');
    }
  } catch (error) {
    console.error("An error occurred while updating a todo:", error);
    throw error;
  }
};

export const deleteTodo = async (id: number): Promise<void> => {
  try {
    const response = await fetch(`${API_URL}/todo/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error('Failed to delete todo');
    }
  } catch (error) {
    console.error("An error occurred while deleting a todo:", error);
    throw error;
  }
};

export const fetchMessage = async (): Promise<string> => {
  try {
    const response = await fetch(`${API_URL}/helloworld`);
    const data = await response.json();
    return `Hello ${data.message}`;
  } catch (error) {
    console.error("An error occurred while fetching the message:", error);
    return "Hello World!";
  }
};
