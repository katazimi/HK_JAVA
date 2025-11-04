import { renderBoardList } from './ui/boardListView.js';

//addEventListener객체(이벤트를 등록하고 해당 기능을 실행)
document.addEventListener('DOMContentLoaded', () => {
	console.log("동작");
    const container = document.getElementById('boardlist');
    renderBoardList(container);
});
