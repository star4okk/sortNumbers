(def channelOfNumbers (chan 10))

(go (>! channelOfNumbers 18))
(go (>! channelOfNumbers 14))
(go (>! channelOfNumbers 27))
(go (>! channelOfNumbers 7))

(defn sortNumbersFromChannel [chanOfNumbers number]
  (def channelOfNumbersWithRemainder (chan 10))
  (def channelOfNumbersWithoutRemainder (chan 10))
  (go-loop []
           (when-let [num (<! chanOfNumbers)]
             (do
               (if (= (rem num number) 0)
                 (>! channelOfNumbersWithoutRemainder num)
                 (>! channelOfNumbersWithRemainder num)
                 )
               (recur))
             )
           )
  [channelOfNumbersWithoutRemainder channelOfNumbersWithRemainder]
  )