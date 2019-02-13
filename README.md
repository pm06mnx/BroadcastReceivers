# BroadcastReceivers
Домашнее задание по теме "BroadcastReceivers"

* Реализовать интент сервис, который будет обрабатывать входящие интенты, и в зависимости от значения в интенте, менять состояние в синглтон менеджере состояний.
* Менеджер состояний будет иметь 5 состояний(A->B->C->D->E), и два метода, один для получения текущего состояния, второй для изменения состояния.
* Интент сервис, после изменения состояния должен разослать с помощью широковещательных сообщений всем подписчикам новое состояние менеджера.
* Активити которое подписано на широковещательные сообщения, должно при получении вывести в TextView новое состояние
* Также активити должна иметь кнопку по нажатию на которую будет сгенерирован интент для изменения состояния менеджера, и отправлен в сервис.