# ZurLeerenStunde
Die Welt der Twitterbots ist voll von Zeitansagen - ob [@koelner_dom](https://twitter.com/koelner_dom), diverse andere Kirchen oder halt [@ZurVollenStunde](https://twitter.com/zurvollenstunde). 

Das Problem mit diesen Zeitansagern ist bloß: Sie sind allesamt nützlich. ZurLeerenStunde löst dieses Problem, indem es nur gelegentlich die richtige Uhrzeit twittert.

## Funktion
ZLS twittert in zufälligen Zeitabständen, die zwischen den Werten *min* und *max* aus der Konfigurationsdatei liegen. Hierbei können mit gleicher Wahrscheinlichkeit folgende Fälle auftreten:

* **tatsächliche Uhrzeit**: Die aktuelle Uhrzeit wird getwittert.
* **geänderte Uhrzeit**: Eine ähnliche Uhrzeit (+/- 15 min) wird getwittert.
* **zufällige Uhrzeit**: Eine vollkommen zufällige Uhrzeit wird getwittert.

## Konfiguration
Der Bot verlangt eine Konfigurationsdatei, die als *config.json* gespeichert werden sollte.
Eine Beispieldatei ist folgende:
```json
{
  "consumer-key": "TWITTER_CONSUMER_KEY",
  "consumer-secret": "TWITTER_CONSUMER_SECRET",
  "access-token": "TWITTER_ACCESS_TOKEN",
  "access-secret": "TWITTER_ACCESS_TOKEN_SECRET",
  "min": 40,
  "max": 120
}
```
Damit lägen die Tweets zwischen 40 und 120 Minuten auseinander. Tweets gäbe es also nicht öfter als alle 40 Minuten, nicht seltener als alle 2 Stunden.

# Kontakt
Ich bin (hoffentlich auch längerfristig) unter [@antifuse](https://twitter.com/antifuse) zu erreichen.