import React, { useState, useEffect } from 'react';
import { LineChart, Line, BarChart, Bar, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, AreaChart, Area } from 'recharts';

function App() {
  const [currentSlide, setCurrentSlide] = useState(0);
  const [animationKey, setAnimationKey] = useState(0);

  const stockData = [
    { month: 'Jan', price: 150, volume: 2400 },
    { month: 'Feb', price: 165, volume: 2210 },
    { month: 'Mar', price: 180, volume: 2290 },
    { month: 'Apr', price: 175, volume: 2000 },
    { month: 'May', price: 195, volume: 2181 },
    { month: 'Jun', price: 210, volume: 2500 },
    { month: 'Jul', price: 225, volume: 2100 },
    { month: 'Aug', price: 240, volume: 2400 },
  ];

  const marketShare = [
    { name: 'Technology', value: 35, color: '#3b82f6' },
    { name: 'Healthcare', value: 25, color: '#8b5cf6' },
    { name: 'Finance', value: 20, color: '#10b981' },
    { name: 'Energy', value: 12, color: '#f59e0b' },
    { name: 'Others', value: 8, color: '#ef4444' },
  ];

  const performanceData = [
    { quarter: 'Q1', revenue: 45000, profit: 12000 },
    { quarter: 'Q2', revenue: 52000, profit: 15000 },
    { quarter: 'Q3', revenue: 61000, profit: 18000 },
    { quarter: 'Q4', revenue: 70000, profit: 22000 },
  ];

  const slides = [
    {
      title: 'Stock Market Analysis',
      subtitle: 'Comprehensive Market Overview 2024',
      type: 'cover',
    },
    {
      title: 'Market Performance',
      subtitle: 'Stock Price Trends Over Time',
      type: 'chart',
      chart: 'line',
    },
    {
      title: 'Market Distribution',
      subtitle: 'Sector-wise Market Share',
      type: 'chart',
      chart: 'pie',
    },
    {
      title: 'Quarterly Performance',
      subtitle: 'Revenue & Profit Analysis',
      type: 'chart',
      chart: 'bar',
    },
    {
      title: 'Key Insights',
      subtitle: 'Market Highlights & Trends',
      type: 'insights',
    },
    {
      title: 'Thank You',
      subtitle: 'Questions & Discussion',
      type: 'end',
    },
  ];

  useEffect(() => {
    setAnimationKey(prev => prev + 1);
  }, [currentSlide]);

  const nextSlide = () => {
    if (currentSlide < slides.length - 1) {
      setCurrentSlide(currentSlide + 1);
    }
  };

  const prevSlide = () => {
    if (currentSlide > 0) {
      setCurrentSlide(currentSlide - 1);
    }
  };

  const goToSlide = (index) => {
    setCurrentSlide(index);
  };

  useEffect(() => {
    const handleKeyPress = (e) => {
      if (e.key === 'ArrowRight') nextSlide();
      if (e.key === 'ArrowLeft') prevSlide();
    };
    window.addEventListener('keydown', handleKeyPress);
    return () => window.removeEventListener('keydown', handleKeyPress);
  }, [currentSlide]);

  const renderSlide = () => {
    const slide = slides[currentSlide];

    switch (slide.type) {
      case 'cover':
        return (
          <div className="flex flex-col items-center justify-center h-full text-center px-8">
            <div key={animationKey} className="animate-scale-in">
              <h1 className="text-7xl font-bold text-white mb-6 drop-shadow-2xl">
                {slide.title}
              </h1>
              <p className="text-3xl text-blue-100 mb-12 animate-fade-in">
                {slide.subtitle}
              </p>
              <div className="flex gap-4 justify-center animate-slide-up">
                <div className="bg-white/20 backdrop-blur-sm px-8 py-4 rounded-lg border border-white/30">
                  <p className="text-5xl font-bold text-white">+24%</p>
                  <p className="text-lg text-blue-100 mt-2">Growth</p>
                </div>
                <div className="bg-white/20 backdrop-blur-sm px-8 py-4 rounded-lg border border-white/30">
                  <p className="text-5xl font-bold text-white">$2.5B</p>
                  <p className="text-lg text-blue-100 mt-2">Market Cap</p>
                </div>
                <div className="bg-white/20 backdrop-blur-sm px-8 py-4 rounded-lg border border-white/30">
                  <p className="text-5xl font-bold text-white">150K</p>
                  <p className="text-lg text-blue-100 mt-2">Investors</p>
                </div>
              </div>
            </div>
          </div>
        );

      case 'chart':
        return (
          <div className="flex flex-col h-full p-12">
            <div key={animationKey} className="animate-slide-up">
              <h2 className="text-5xl font-bold text-white mb-3">{slide.title}</h2>
              <p className="text-2xl text-blue-100 mb-8">{slide.subtitle}</p>
            </div>
            <div className="flex-1 bg-white/10 backdrop-blur-sm rounded-2xl p-8 border border-white/20 animate-fade-in" style={{ minHeight: '400px' }}>
              {slide.chart === 'line' && (
                <ResponsiveContainer width="100%" height={500}>
                  <AreaChart data={stockData}>
                    <defs>
                      <linearGradient id="colorPrice" x1="0" y1="0" x2="0" y2="1">
                        <stop offset="5%" stopColor="#3b82f6" stopOpacity={0.8}/>
                        <stop offset="95%" stopColor="#3b82f6" stopOpacity={0}/>
                      </linearGradient>
                    </defs>
                    <CartesianGrid strokeDasharray="3 3" stroke="#ffffff30" />
                    <XAxis dataKey="month" stroke="#ffffff" style={{ fontSize: '14px' }} />
                    <YAxis stroke="#ffffff" style={{ fontSize: '14px' }} />
                    <Tooltip 
                      contentStyle={{ 
                        backgroundColor: '#1e293b', 
                        border: '1px solid #3b82f6',
                        borderRadius: '8px',
                        color: '#fff'
                      }} 
                    />
                    <Area type="monotone" dataKey="price" stroke="#3b82f6" strokeWidth={3} fillOpacity={1} fill="url(#colorPrice)" />
                  </AreaChart>
                </ResponsiveContainer>
              )}
              {slide.chart === 'pie' && (
                <ResponsiveContainer width="100%" height={500}>
                  <PieChart>
                    <Pie
                      data={marketShare}
                      cx="50%"
                      cy="50%"
                      labelLine={false}
                      label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                      outerRadius={180}
                      fill="#8884d8"
                      dataKey="value"
                    >
                      {marketShare.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={entry.color} />
                      ))}
                    </Pie>
                    <Tooltip 
                      contentStyle={{ 
                        backgroundColor: '#1e293b', 
                        border: '1px solid #3b82f6',
                        borderRadius: '8px',
                        color: '#fff'
                      }} 
                    />
                  </PieChart>
                </ResponsiveContainer>
              )}
              {slide.chart === 'bar' && (
                <ResponsiveContainer width="100%" height={500}>
                  <BarChart data={performanceData}>
                    <CartesianGrid strokeDasharray="3 3" stroke="#ffffff30" />
                    <XAxis dataKey="quarter" stroke="#ffffff" style={{ fontSize: '14px' }} />
                    <YAxis stroke="#ffffff" style={{ fontSize: '14px' }} />
                    <Tooltip 
                      contentStyle={{ 
                        backgroundColor: '#1e293b', 
                        border: '1px solid #3b82f6',
                        borderRadius: '8px',
                        color: '#fff'
                      }} 
                    />
                    <Legend wrapperStyle={{ color: '#fff' }} />
                    <Bar dataKey="revenue" fill="#3b82f6" radius={[8, 8, 0, 0]} />
                    <Bar dataKey="profit" fill="#10b981" radius={[8, 8, 0, 0]} />
                  </BarChart>
                </ResponsiveContainer>
              )}
            </div>
          </div>
        );

      case 'insights':
        return (
          <div className="flex flex-col h-full p-12">
            <div key={animationKey} className="animate-slide-up">
              <h2 className="text-5xl font-bold text-white mb-3">{slide.title}</h2>
              <p className="text-2xl text-blue-100 mb-8">{slide.subtitle}</p>
            </div>
            <div className="grid grid-cols-2 gap-6 flex-1">
              <div className="bg-white/10 backdrop-blur-sm rounded-2xl p-8 border border-white/20 animate-slide-right" style={{ animationDelay: '0.1s' }}>
                <div className="text-5xl mb-4">📈</div>
                <h3 className="text-2xl font-bold text-white mb-3">Strong Growth</h3>
                <p className="text-lg text-blue-100">Market shows consistent upward trend with 24% year-over-year growth</p>
              </div>
              <div className="bg-white/10 backdrop-blur-sm rounded-2xl p-8 border border-white/20 animate-slide-right" style={{ animationDelay: '0.2s' }}>
                <div className="text-5xl mb-4">💼</div>
                <h3 className="text-2xl font-bold text-white mb-3">Diverse Portfolio</h3>
                <p className="text-lg text-blue-100">Well-balanced sector distribution minimizes risk exposure</p>
              </div>
              <div className="bg-white/10 backdrop-blur-sm rounded-2xl p-8 border border-white/20 animate-slide-right" style={{ animationDelay: '0.3s' }}>
                <div className="text-5xl mb-4">💰</div>
                <h3 className="text-2xl font-bold text-white mb-3">Revenue Growth</h3>
                <p className="text-lg text-blue-100">Quarterly revenue increased by 55% from Q1 to Q4</p>
              </div>
              <div className="bg-white/10 backdrop-blur-sm rounded-2xl p-8 border border-white/20 animate-slide-right" style={{ animationDelay: '0.4s' }}>
                <div className="text-5xl mb-4">🎯</div>
                <h3 className="text-2xl font-bold text-white mb-3">Future Outlook</h3>
                <p className="text-lg text-blue-100">Positive market indicators suggest continued growth potential</p>
              </div>
            </div>
          </div>
        );

      case 'end':
        return (
          <div className="flex flex-col items-center justify-center h-full text-center px-8">
            <div key={animationKey} className="animate-scale-in">
              <h1 className="text-7xl font-bold text-white mb-6 drop-shadow-2xl">
                {slide.title}
              </h1>
              <p className="text-3xl text-blue-100 mb-12">
                {slide.subtitle}
              </p>
              <div className="text-6xl mb-8 animate-bounce">🙏</div>
              <p className="text-xl text-blue-100">
                For more information, contact us at info@stockmarket.com
              </p>
            </div>
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <div className="relative w-screen h-screen overflow-hidden bg-gradient-to-br from-blue-900 via-blue-800 to-indigo-900">
      {renderSlide()}

      {/* Navigation Controls */}
      <div className="absolute bottom-8 left-1/2 transform -translate-x-1/2 flex items-center gap-4 bg-white/10 backdrop-blur-sm px-6 py-3 rounded-full border border-white/20">
        <button
          onClick={prevSlide}
          disabled={currentSlide === 0}
          className="text-white hover:text-blue-300 disabled:opacity-30 disabled:cursor-not-allowed transition-all text-2xl px-3"
        >
          ←
        </button>
        
        <div className="flex gap-2">
          {slides.map((_, index) => (
            <button
              key={index}
              onClick={() => goToSlide(index)}
              className={`w-3 h-3 rounded-full transition-all ${
                currentSlide === index 
                  ? 'bg-white w-8' 
                  : 'bg-white/40 hover:bg-white/60'
              }`}
            />
          ))}
        </div>

        <button
          onClick={nextSlide}
          disabled={currentSlide === slides.length - 1}
          className="text-white hover:text-blue-300 disabled:opacity-30 disabled:cursor-not-allowed transition-all text-2xl px-3"
        >
          →
        </button>
      </div>

      {/* Slide Counter */}
      <div className="absolute top-8 right-8 bg-white/10 backdrop-blur-sm px-4 py-2 rounded-lg border border-white/20">
        <p className="text-white text-lg font-semibold">
          {currentSlide + 1} / {slides.length}
        </p>
      </div>

      {/* Instructions */}
      <div className="absolute top-8 left-8 bg-white/10 backdrop-blur-sm px-4 py-2 rounded-lg border border-white/20">
        <p className="text-white text-sm">
          Use ← → arrows or click dots to navigate
        </p>
      </div>
    </div>
  );
}

export default App;
