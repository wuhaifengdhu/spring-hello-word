function getSalt() {
    return "$2a$11$o8vgy4olY7wcraHQKm4sqO";
}

Array.prototype.remove = function(from, to) {
	var rest = this.slice((to || from) + 1 || this.length);
	this.length = from < 0 ? this.length + from : from;
	return this.push.apply(this, rest);
};