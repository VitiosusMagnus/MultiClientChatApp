# MultiClient Chat App (Java)

A simple multi-client chat application implemented in Java.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [How to Use](#how-to-use)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This Java-based multi-client chat application allows users to chat with each other over a network. It consists of both a server and a client component, where multiple clients can connect to the server and chat in real-time. This README provides instructions on how to set up and use the application.

## Features

- Multi-client support: Allows multiple clients to connect to the chat server.
- Real-time messaging: Users can send and receive messages in real-time.
- Simple GUI: The client application provides a graphical user interface for ease of use.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK): You must have JDK installed to compile and run the application.
- Java Swing Library: The client application uses Java Swing for its graphical user interface.

## How to Use

Follow these steps to set up and run the multi-client chat application:

1. **Compile the Code:**

   ```shell
   javac server/ChatServer.java
   javac client/ChatClient.java
   ```

2. **Run the Server:**

   Start the chat server by running the following command:

   ```shell
   java server.ChatServer
   ```

   The server will start listening on port 9999.

3. **Run Client Applications:**

    - Launch one or more client applications by running:

      ```shell
      java client.ChatClientGUI
      ```

    - When prompted, provide a unique nickname for each client.

4. **Chatting:**

    - In each client's GUI, you can type messages in the text field at the bottom.
    - Click the "Send" button to send a message.
    - Messages sent by one client will be visible to all connected clients.

5. **Closing Clients:**

   To close a client application, simply close its GUI window.

6. **Stopping the Server:**

   To stop the server, press `Ctrl+C` in the terminal where the server is running.

## Screenshots



## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these guidelines:

- Fork the repository.
- Create a new branch for your feature or bug fix.
- Make your changes and ensure the code is well-documented.
- Test your changes thoroughly.
- Create a pull request with a clear description of your changes.

## License

This project is licensed under the [MIT License](LICENSE).

---
