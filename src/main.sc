require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        q!: хочу пиццу
        script: 
            $reactions.answer('Choose')
            $reactions.buttons([{text: 'Пицца', transition: '/Pizza'}, {text: 'Роллы', transition: '/Susi'}])

    state: Pizza
        script: 
            $reactions.answer('Choose')
            $reactions.buttons([{text: 'С мясом', transition: '/Pizza'}, {text: 'С колбасой', transition: '/Susi'}])


    state: Susi
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}