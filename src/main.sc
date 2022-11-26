require: slotfilling/slotFilling.sc
  module = sys.zb-common
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

    state: NoMatch
        event!: noMatch
        a: Я не понял.

    state: Match
        event!: match
        a: {{$context.intent.answer}}