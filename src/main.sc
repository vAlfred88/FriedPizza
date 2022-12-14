require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: pizza.csv
    name = pizza
    var = pizza
  
theme: /

    state: Start
        q!: $regex</start>
        q!: хочу пиццу
        script: 
            $reactions.answer('Привет, я FriedPizza_Bot')
            $reactions.answer('Что будете заказывать?')
            $reactions.buttons(
                [
                    {text: 'Пицца', transition: '/Pizza'}, 
                    {text: 'Роллы', transition: '/Susi'}
                ]
            )

    state: Pizza
        script: 
            $reactions.answer('Вот что мы можем предложить')
            $reactions.buttons(
                [
                    {text: 'С мясом', transition: '/Chosed'}, 
                    {text: 'С колбасой', transition: '/Chosed'},
                    {text: 'Прочее', transition: '/Chosed'}
                ]
            )


    state: Susi
        script: 
            $reactions.answer('Вот что мы можем предложить')
            $reactions.buttons(
                [
                    {text: 'Филадельфия', transition: '/Chosed'}, 
                    {text: 'Фирменные', transition: '/Chosed'},
                    {text: 'Классические роллы', transition: '/Chosed'}
                ]
            )
    
    state: Chosed
        a: И что мне делать дальше?
        script: 
            for (var i = 1; i < Object.keys(pizza).length + 1; i++) {
                var button_name = pizza[i].value.title + " за " + pizza[i].value.price + " руб."
                $reactions.answer(pizza[i].value.description)
                $reactions.inlineButtons({text: button_name,  callback_data: i })
            }
    
    state: GetVariant
        event: telegramCallbackQuery
        script:
            $session.pizza_id = parseInt($request.query);
            $reactions.answer("Вы заказали: " + pizza[$session.pizza_id].value.title)

    state: NoMatch
        event!: noMatch
        a: Я не понял.

    state: Match
        event!: match
        a: {{$context.intent.answer}}