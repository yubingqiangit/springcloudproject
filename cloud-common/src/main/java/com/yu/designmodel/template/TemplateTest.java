package com.yu.designmodel.template;

/**
 * 摸板方法测试类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/24 17:30 
 */
public class TemplateTest {
    public static void main(String[] args) {
       /* AbsTemplateBase potato = new Potato(0);
        potato.todo();*/
        AbsTemplateBase tomato = new Tomato(0);
        tomato.todo();
    }
}
