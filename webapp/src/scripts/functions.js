
/**
 * dado um array "array", devolve uma cópia do array
 * com os primeiros "limit" elementos
 * 
 * @param {array} array
 * @param {number} limit
 * 
 */
export function limitArrayToFirstX(array, limit){
    return array.slice(0, limit);
}


// dado o timestamp das leituras da API, converte para um objeto Date
export function timestampToDate(timestamp){
  return new Date(timestamp*1000); // converter para milissegundos
}

// dado o timestamp das leituras da API, devolve uma string com horas e minutos
export function timestampToHoursMinutesSeconds(timestamp) {
  let dateObject = new Date(timestamp * 1000); // converter para milissegundos
  const horas = dateObject.getHours();
  const minutos = dateObject.getMinutes();
  const segundos = dateObject.getSeconds();
  return horas + ":" + minutos + ":" + segundos;

}