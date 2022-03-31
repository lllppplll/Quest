<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>Bridge</title>

<link rel="shortcut icon" href="URL/image/favicon/favicon.ico"  type="image/x-icon"/>

<link rel="stylesheet" href="URL/css/bridge.css">

<link href="//db.onlinewebfonts.com/c/ac63b6058f703ae46123ce6c383c6287?family=Kristen+ITC" rel="stylesheet" type="text/css"/>

</head>
<body>

<h1 class="bridgeTitle" >Bridge</h1>

<a class="home_button" href="./" >Home</a>
<!-- <a class="back_button" href="./mathsGames" >Back</a> -->

<div></div>


<!-- <div id="sprite_box_art" class="sprite_box_art" >
<img class="sprite_sheet_art" src="URL/image/MathsGames/ball.png" />
</div> -->

<!-- pop up screen instructions -->
<div class="instructions_screen" >
<h3>Help your character to cross the bridge by answering the Maths questions.</h3>
<a class="instructions_button" onClick="instructions()" >OK</a>
</div>


<!-- pop up screens -->

<!--  start pop up  -->
<div id="options_start_screen"  >
<h1 class="selectTitle" >Select</h1>

<div class="numbersBox">
<h1>Numbers</h1>
<div class="radioNumbers" >
<input type="radio" id="ten" name="selectNumber" value="10" checked="checked">
<label for="ten">0-10</label>
<input type="radio" id="twenty" name="selectNumber" value="20">
<label for="twenty">0-20</label>
</div>
</div>

<div class="operationsBox" >
<h1>Operation</h1>
<div class="radioOperations" >
<input type="radio" id="add" name="operation" value="add" checked="checked">
<label for="add">&#43;</label>
<input type="radio" id="subtract" name="operation" value="subtract"  >
<label for="subtract">&#8722;</label></div>
</div>

<div class="charactersBox" >
<h1>Character</h1>
<div class="radioCharacter" >
<input type="radio" id="MrBall" name="character" value="ball" checked="checked">
<label class="MrBox_small" for="MrBall">Mr Circle</label>
<input type="radio" id="MsTriangle" name="character" value="triangle"  >
<label class="MsTriangle_small" for="MsTriangle">Ms Triangle</label>
<input type="radio" id="MrBox" name="character" value="box"  >
<label class="MrBox_small" for="MrBox">Mr Square</label>
</div>
</div>

<div class="timerBox" >
<h1>Timer</h1>
<div class="radioTimer" >
<input type="radio" id="countdownOff" name="timer" value="box" checked="checked">
<label for="Timer">Off</label>
<input type="radio" id="countdownNumber" name="timer" value="box"  >
<label for="Timer">10 seconds</label>
</div>
</div>

<div class="selectButtons" >
<a class="pop_up_buttons" href="./" >Exit</a>
<a class="pop_up_buttons" onClick="startingMainGame()" >Play</a>
</div>

</div>

<!--  -->
<!--  fallen pop up-->
<div id="fallen_screen"  >
<h1 class="gameOverTitle" >Game Over</h1>

<h1 class="player_name" >${playerName}</h1>

<div class="scoreBox" >
<br/>
<h1>Score</h1>
<div  class="scoreUser" >
<p id="userScore" class="scoreUserA" >A</p><p class="scoreUserB">/</p><p  class="scoreUserC" >5</p>
</div>
</div>

<div class="yourAnswerBox" >
<h1>Your answer</h1>
<div class="numbersIncorrect" >
<p class="NumberAnswerA" >A</p><p class="NumberAnswerSign">+-</p><p class="NumberAnswerB" >B</p><p class="NumberAnswerEquals" >=</p><p id="numberAnswerC" class="NumberAnswerC" >?</p><p class="NumberCross" >&#10008;</p>
</div>
</div>

<div class="correctAnswerBox" >
<h1>Correct answer</h1>
<div class="numbersCorrect" >
<p class="NumberAnswerA" >A</p><p class="NumberAnswerSign">+-</p><p class="NumberAnswerB" >B</p><p class="NumberAnswerEquals" >=</p><p id="numberAnswerUserC" class="NumberAnswerUserC" >?</p><p  class="NumberTick" >&#10004;</p>
</div>
</div>

<div class="fallenButtons" >
<a class="pop_up_buttons" href="./" >Exit</a>
<!-- <a class="pop_up_buttons" href="./bridge" >Play</a>
 -->
 <a class="pop_up_buttons" onClick="backToStart()" >Play</a>
</div>

</div>

<!-- finished pop up Well done -->
<div id="well_done_screen"  >
<h1 class="wellDoneTitle" >Well Done!</h1>

<h1 class="player_name" >${playerName}</h1>

<div class="wellDoneScoreBox">
<h1  >Score</h1>
<p class="wellDoneScore" >5/5</p>
</div>

<div class="wellDoneButtons" >
<a class="pop_up_buttons" href="./" >Exit</a>
<!-- <a class="pop_up_buttons" href="./bridge" >Play</a>
 -->
  <a class="pop_up_buttons" onClick="backToStart()" >Play</a>
 
 </div>
</div>

<div class="bridge_box" >

<!-- character -->
<div id="sprite_box" class="sprite_box sprite_box_stepwise sprite_box_fallen sprite_box_end">
<div id="sprite_MrBall" ></div>
<div id="sprite_MsTriangle" ></div>
<div id="sprite_MrBox" ></div>
</div>
<div></div>

<!-- bridge -->
<div id="first_block" class="block" ></div>
<div id="step1" class="step" ></div>
<div id="step2" class="step" ></div>
<div id="step3" class="step" ></div>
<div id="step4" class="step" ></div>
<div class="block" ></div>

<div></div>

<!--  starting timer number-->
<div id="countdownBox" class="countdown" >10</div>


</div><!--  end of bridge box  -->


<!-- <div></div> -->

<div id="numberAnswerBox" >

<!-- question box -->
<div class="question_MainBox" >

<div class="inside_question_box">
<div id="questionA" class="questionBox" ></div>
<div id="questionSign" class="questionBox"></div>
<div id="questionB" class="questionBox"></div>
 </div>
</div>


<div></div>

<!--  -->

<div id="zeroToTenNumbers" >
<button  id="zero" class="answerNumbers" onClick="numberAnswerBox(0)" >0</button>
<button id="one" class="answerNumbers" onClick="numberAnswerBox(1)" >1</button>
<button id="two" class="answerNumbers" onClick="numberAnswerBox(2)" >2</button>
<button id="three" class="answerNumbers" onClick="numberAnswerBox(3)" >3</button>
<button id="four" class="answerNumbers" onClick="numberAnswerBox(4)" >4</button>
<button id="five" class="answerNumbers" onClick="numberAnswerBox(5)" >5</button>
<button id="six" class="answerNumbers" onClick="numberAnswerBox(6)" >6</button>
<button id="seven" class="answerNumbers" onClick="numberAnswerBox(7)" >7</button>
<button id="eight" class="answerNumbers" onClick="numberAnswerBox(8)" >8</button>
<button id="nine" class="answerNumbers" onClick="numberAnswerBox(9)" >9</button>
<button id="tenButtonTen" class="answerNumbers" onClick="numberAnswerBox(10)" >10</button>
</div>

<div id="tenToTwentyNumbers" >
<!-- <button id="tenButtonTwenty" class="answerNumbers" onClick="numberAnswerBox(10)" >10</button> -->
<button id="eleven" class="answerNumbers" onClick="numberAnswerBox(11)" >11</button>
<button id="twelve" class="answerNumbers" onClick="numberAnswerBox(12)" >12</button>
<button id="thirteen" class="answerNumbers" onClick="numberAnswerBox(13)" >13</button>
<button id="fourteen" class="answerNumbers" onClick="numberAnswerBox(14)" >14</button>
<button id="fifteen" class="answerNumbers" onClick="numberAnswerBox(15)" >15</button>
<button id="sixteen" class="answerNumbers" onClick="numberAnswerBox(16)" >16</button>
<button id="seventeen" class="answerNumbers" onClick="numberAnswerBox(17)" >17</button>
<button id="eighteen" class="answerNumbers" onClick="numberAnswerBox(18)" >18</button>
<button id="nineteen" class="answerNumbers" onClick="numberAnswerBox(19)" >19</button>
<button id="twenty20" class="answerNumbers" onClick="numberAnswerBox(20)" >20</button>
<button id="twentyOne" class="answerNumbers" onClick="numberAnswerBox(21)" >21</button>

</div>

<!--swap button to change numbers from 0-10 to 0-20  -->
<div> <button id="swapNumbers_button" onClick="swapButton()" >Swap</button> </div>


</div><!--  end of number question box  -->

<div></div>

<audio id="correct" src="URL/sound/correct.mp3" preload="auto"></audio>
<audio id="incorrect" src="URL/sound/incorrect.mp3" preload="auto"></audio>
<audio id="end" src="URL/sound/end.mp3" preload="auto"></audio>
<audio id="well_done" src="URL/sound/well_done.mp3" preload="auto"></audio>

<p id="screen-width" ></p>

<script type="text/javascript" src="URL/javascript/bridge.js" ></script>

</body>
</html>