import React, { useEffect, useState } from 'react';
import './styles.css';
import StepsHeader from './StepsHeaders';
import ProductsList from './ProductsList';
import { Product } from './types';
import { fetchProducts } from '../api';

function Orders() {
  const [products, setProducts] = useState<Product[]>([]);
  //console.log(products);

  // useEffect(() => {
  //   fetchProducts()
  //     .then((response) => setProducts(response.data))
  //     .catch((error) => console.log(error));
  // }, []);

  useEffect(() => {
    const initializeProducts = async () => {
      try {
        const response = await fetchProducts();
        setProducts(response.data);
        //console.log(response);
      } catch (e) {
        console.log(e);
      }
    };

    initializeProducts();
  }, []);

  return (
    <div className="orders-container">
      <StepsHeader />
      <ProductsList products={products} />
    </div>
  );
}

export default Orders;
