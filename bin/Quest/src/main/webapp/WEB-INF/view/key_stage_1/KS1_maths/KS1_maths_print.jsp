<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quest: Key Stage 1: Maths</title>
<link rel="stylesheet" href="URL/css/KS1_maths_print.css"media="screen" >
<link rel="stylesheet" href="URL/css/KS1_maths_print2.css" media="print" >
</head>
<body>
<h1>Key Stage 1: Maths: Questions.</h1>
<a id="home_button" href="./">Home</a>
<br/><br/>
<label id="add_button" for="add_button">Add</label>
<input type="radio" id="add_button" name="sign" value="Add" onclick="add_checked()"  checked="checked">
<label id="subtract_button" for="subtract_button">Subtract</label>
<input type="radio" id="subtract_button" name="sign" value="Subtract" onclick="subtract_checked()" >
<br/><br/>
<button id="print_button" onclick="window.print()">Print this page</button>

<br/><br/>
<h1 id="name" >Name: ____________________.</h1>
<h1 id="score" >Score: ___ / 10.</h1>
<div id="number_container" >
<div id="question1" ><span class="number" >1. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question2" ><span class="number" >2. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question3" ><span class="number" >3. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question4" ><span class="number" >4. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question5" ><span class="number" >5. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question6" ><span class="number" >6. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question7" ><span class="number" >7. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question8" ><span class="number" >8. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question9" ><span class="number" >9. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
<div id="question10" ><span class="number" >10. </span><span class="questionA" >a</span><span class="sign" >+</span><span class="questionB" >b</span><span> =  ___.</span></div>
</div>

<h1 class="page-break" id="answers_title" >Key Stage 1: Maths: Answers.</h1>

<div id="answer_container" >
<div id="question1a" ><span class="number" >1. </span><span class="answer" >a</span></div>
<div id="question2a" ><span class="number" >2. </span><span class="answer" >a</span></div>
<div id="question3a" ><span class="number" >3. </span><span class="answer" >a</span></div>
<div id="question4a" ><span class="number" >4. </span><span class="answer" >a</span></div>
<div id="question5a" ><span class="number" >5. </span><span class="answer" >a</span></div>
<div id="question6a" ><span class="number" >6. </span><span class="answer" >a</span></div>
<div id="question7a" ><span class="number" >7. </span><span class="answer" >a</span></div>
<div id="question8a" ><span class="number" >8. </span><span class="answer" >a</span></div>
<div id="question9a" ><span class="number" >9. </span><span class="answer" >a</span></div>
<div id="question10a" ><span class="number" >10. </span><span class="answer" >a</span></div>
</div>

<br/><br/>

<script src="URL/javascript/KS1_maths_print.js" ></script>
</body>
</html>