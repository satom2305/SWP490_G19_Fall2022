import axios from 'axios'

class ClientService {
  async getListProduct() {
    const res = await axios.get(
      'https://capstone-project-tw.herokuapp.com/rest/products/listProduct'
    )
    return res.data
  }

  async getListCategory() {
    const res = await axios.get(
      'https://capstone-project-tw.herokuapp.com/rest/categories'
    )
    return res.data
  }

}
export const clientService = new ClientService()
