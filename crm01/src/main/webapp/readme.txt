1.jquery中prop()和attr()方法的区别？
prop()获取的是dom对象中的固有属性
attr()获取的是dom对象中的自定义属性
2.jquery中parseInt()方法的作用？
将一个浮点数去掉小数部分，将合适的字符串转换为一个整数
3.在idea中明明加载了前端的插件却没有生效？
将maven的目录结构重新刷新后再重启http服务器
4.对于动态生成的元素，如何触发事件？如何往方法中传递参数？
直接在尖括号中触发(直接触发)，硬性规定：传递的参数必须包裹在字符串中
错误示范：'<a class="myHref" href="javascript:void(0);" onclick="deleteRemark('+n.id+')"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #FF0000;"></span></a>'
正确范例：'<a class="myHref" href="javascript:void(0);" onclick="deleteRemark(\''+n.id+'\')"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #FF0000;"></span></a>'