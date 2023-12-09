<table><tr><td> <em>Assignment: </em> IT114 Trivia Milestone 2</td></tr>
<tr><td> <em>Student: </em> Ayanfeolu Olunuga (ajo47)</td></tr>
<tr><td> <em>Generated: </em> 12/10/2023 2:32:55 AM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-trivia-milestone-2/grade/ajo47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp;<a href="https://docs.google.com/document/d/1h2aEWUoZ-etpz1CRl-StaWbZTjkd9BDMq0b6TXK4utI/view">https://docs.google.com/document/d/1h2aEWUoZ-etpz1CRl-StaWbZTjkd9BDMq0b6TXK4utI/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T20.31.19image.png.webp?alt=media&token=aa29b601-dc78-4681-b9e3-a5c3fbbf4259"/></td></tr>
<tr><td> <em>Caption:</em> <p>The screenshot shows the payload of type Answer which is passed to the<br>game when its active. <br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Game Play Code </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code related to the question/category choice</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T20.36.13image.png.webp?alt=media&token=6dbb2701-f9e1-4bfd-a4b8-831a6a7b0d7a"/></td></tr>
<tr><td> <em>Caption:</em> <p>This screenshot shows how the system selects a random category and a random<br>question<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T20.37.38image.png.webp?alt=media&token=24c3911d-5d24-4a27-b42d-149d97f0a75d"/></td></tr>
<tr><td> <em>Caption:</em> <p>This screenshot shows how the program passes the question to the user when<br>start method is invoked<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show the code related to players picking answers</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T20.49.33image.png.webp?alt=media&token=f279b4a0-f672-4cc0-b8d6-8a0dbea04ba2"/></td></tr>
<tr><td> <em>Caption:</em> <p>The system treats every message that starts with /guess as a command and<br>passes it to the gameroom<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T20.54.36image.png.webp?alt=media&token=ecdbbd68-8745-4320-8d2b-d4595b552c20"/></td></tr>
<tr><td> <em>Caption:</em> <p>The server awards points based on the time remaining for gameround to end.<br><br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.02.02image.png.webp?alt=media&token=6408b3d2-63e8-4e66-a739-214cde25ba71"/></td></tr>
<tr><td> <em>Caption:</em> <p>The methods enforce the restriction of only one entry per round<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.05.55image.png.webp?alt=media&token=d89fe2cb-0509-41f5-95c6-cf6fdb30a6ed"/></td></tr>
<tr><td> <em>Caption:</em> <p>The game runs out of time after 30 seconds<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.10.54image.png.webp?alt=media&token=3a3e4792-2a1b-401a-a862-658ede693e60"/></td></tr>
<tr><td> <em>Caption:</em> <p>The logic checks if number of players who have made guesses are equal<br>to the number of players in the session, and if true the resetsession()<br>method is invoked<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.18.06image.png.webp?alt=media&token=56eed06d-800e-4769-ae66-00428e672b3e"/></td></tr>
<tr><td> <em>Caption:</em> <p>When the game ends, the resetsession() method is called, and the scores stored<br>in the timeofguess list<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Show the code related to the timer functionality</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.31.03image.png.webp?alt=media&token=d3c7a7ca-52f4-4633-8151-be11fe094bf4"/></td></tr>
<tr><td> <em>Caption:</em> <p>The timer and countdown funtionality<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.37.48image.png.webp?alt=media&token=9cde9ae4-962d-419d-9a32-73415d64c427"/></td></tr>
<tr><td> <em>Caption:</em> <p>When the set time(30 seconds) expires, the resetsession() method is called and the<br>cycle restarts<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T21.41.12image.png.webp?alt=media&token=ccb545ce-c1ac-4347-b7dc-ec3cbf28013a"/></td></tr>
<tr><td> <em>Caption:</em> <p>When the gameover condition turns to true before the timer expires, it stops<br>the timer and restarts the game<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 4: </em> Explain the Game flow code at a high level</td></tr>
<tr><td> <em>Response:</em> <div><br></div><div>This&nbsp; game is an interactive client server model where players interact and participate<br>in a game session. The GameRoom class orchestrates the game's flow and manages<br>players' interactions. At the start, it initializes variables to manage game phases, player<br>states, questions, and timers. The main game loop runs via the start method,<br>triggering a 30-second session during which players answer questions. The method receiveAnswerFromClient handles<br>player answers, checks correctness, manages the number of guesses allowed per player, and<br>checks for the game's end condition. When all players have answered or the<br>session timer ends, the resetSession method is invoked, displaying rankings based on player<br>scores (time remaining upon answering). The code uses synchronization steps for player readiness<br>checks and phase updates to ensure all players stay in sync during the<br>game. The GameRoom class manages various functionalities. It initializes player states, tracks readiness,<br>handles player disconnection, and manages the game's progression through different phases (e.g., READY,<br>IN_PROGRESS). It reads questions from a file, sets up a timer for the<br>session, and handles player answers during the game. The synchronization steps ensure that<br>player readiness and phase updates are communicated among all participants.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Game Evidence </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show screenshots of the terminal output of a working game flow</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T23.15.48image.png.webp?alt=media&token=4f286aaa-c694-4808-b395-1ae189859582"/></td></tr>
<tr><td> <em>Caption:</em> <p>The server showing the details about the category, question and available options<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T23.18.45image.png.webp?alt=media&token=db070a56-f4ce-4892-9fec-e4132c9d2c2e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Player submitting answers<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T23.24.14image.png.webp?alt=media&token=05e95454-d80e-4575-b446-915bf63e8ecd"/></td></tr>
<tr><td> <em>Caption:</em> <p>The server sending points to players<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-09T23.26.05image.png.webp?alt=media&token=f3a56c45-327e-4d6b-b822-2588466816f4"/></td></tr>
<tr><td> <em>Caption:</em> <p>Message after only one player is ready<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/Ayanfe0luwanimi/ajo47-IT114-001/pull/12">https://github.com/Ayanfe0luwanimi/ajo47-IT114-001/pull/12</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-trivia-milestone-2/grade/ajo47" target="_blank">Grading</a></td></tr></table>