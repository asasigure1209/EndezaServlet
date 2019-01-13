var ngWords = [
  "ウェイ",
  "パリピ",
  "楽しい"
];

// 入力した値を取得する
var textbox = document.getElementById("text-box");
// share button
var button = document.getElementById("button");

textbox.oninput = function() {
  console.log(!hasRegularText(this.value));
  if (!hasRegularText(this.value)) {
    button.style.visibility = "hidden";
  } else {
    button.style.visibility = "visible";
  }
}

function hasRegularText(text) {
  for (var i = 0; i < ngWords.length; i++) {
    if (text.match(ngWords[i])) {
      return false;
    }
  }

  return true;
}
