/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkSession()
{
username=getSession('username')
if (username!=null && username!="")
  $.login({
      text:username
  })
else 
{
  $.login({
      text:登录
  })
}
}