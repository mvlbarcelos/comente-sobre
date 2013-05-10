function formatUrl(s) {
    var map = [
        ["\\s", "-"],
        ["[àáâãäå]", "a"],
        ["ç", "c"],
        ["[èéêë]", "e"],
        ["[ìíîï]", "i"],
        ["ñ", "n"],
        ["[òóôõö]", "o"],
        ["[ùúûü]", "u"],
        ["[ýÿ]", "y"],
        ["\\W", "-"]
    ];
    for (var i=0; i<map.length; ++i) {
        s = s.replace(new RegExp(map[i][0], "gi"), function(match) {
                return map[i][1];
        });
    }
    return s.toLowerCase();
};