require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        q!: хочу пиццу
        script: 
            $reactions.buttons([{text: 'Пицца', transition: '/Pizza'}, {text: 'Роллы', transition: '/Susi'}])

    state: Pizza
        a: Pizza

    state: Susi
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}